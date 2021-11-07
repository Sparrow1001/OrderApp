package com.example.orderapp.Repository.Room.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.orderapp.Repository.Model.PersonDTO;

@Dao
public interface PersonDAO {

    @Insert
    void addPerson(PersonDTO person);

    @Delete
    void deletePerson(PersonDTO person);

    @Update
    void updatePerson(PersonDTO person);

    @Query("SELECT * FROM 'person' WHERE email = :email AND password = :password")
    LiveData<PersonDTO> checkPerson(String email, String password);

}
