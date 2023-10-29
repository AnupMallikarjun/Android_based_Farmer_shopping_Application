package com.example.agrishop;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class Profile extends AppCompatActivity {
    ImageView profile;
    TextView name,email,number,address;
    Button update;
    FirebaseStorage storage;
    FirebaseUser auth;
    FirebaseDatabase database;
    private String userID;
    private FirebaseUser firebaseUser;
    private DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        storage=FirebaseStorage.getInstance();
        auth= FirebaseAuth.getInstance().getCurrentUser();
        database=FirebaseDatabase.getInstance();
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        reference=FirebaseDatabase.getInstance().getReference("Usersregister");
        userID=firebaseUser.getUid();

        profile=findViewById(R.id.profile_image);
        update=findViewById(R.id.updatebtn);
        name=findViewById(R.id.yourname);
        email=findViewById(R.id.youremail);
        number=findViewById(R.id.yourmobilenumber);
        address=findViewById(R.id.youraddress);


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Usermodel userProfile = snapshot.getValue(Usermodel.class);


                if (userProfile != null){
                    String profilename = userProfile.getName();
                    String profileemail = userProfile.getEmail();
                    String profileumber = userProfile.getPhone();
                    String profileaddress = userProfile.getUseraddress();


                    name.setText(profilename);
                    email.setText(profileemail);
                    number.setText(profileumber);
                    address.setText(profileaddress);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "Something worng happened|", Toast.LENGTH_SHORT).show();
            }
        });







        if (auth != null){
            database.getReference().child("Usersregister").child(auth.getUid())
                    .addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            Usermodel user = snapshot.getValue(Usermodel.class);
                            Glide.with(getApplicationContext()).load(user.getProfileImg()).into(profile);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
        }else {
            Toast.makeText(getApplicationContext(), "gg", Toast.LENGTH_SHORT).show();
        }if (auth == null){
            Intent intent = new Intent(getApplicationContext(), Login_Page.class);
            startActivity(intent);
            finish();
        }



        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);
            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserProfile();

            }
        });

    }

    private void updateUserProfile() {
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData()!= null){
            Uri profileUri = data.getData();
            profile.setImageURI(profileUri);
            final StorageReference reference = storage.getReference().child("profile_picture")
                    .child(FirebaseAuth.getInstance().getUid());
            reference.putFile(profileUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "Sucess", Toast.LENGTH_SHORT).show();
                    reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            database.getReference().child("Usersregister").child(FirebaseAuth.getInstance().getUid()).child("profileImg").setValue(uri.toString());
                            Toast.makeText(getApplicationContext(), "Profile Picture Uploaded", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            });


        }else {
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }


    }
}