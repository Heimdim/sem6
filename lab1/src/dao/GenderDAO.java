package dao;

import entity.Gender;

public interface GenderDAO
{
    boolean addGender(Gender gender);

    boolean removeGender(int id);
}
