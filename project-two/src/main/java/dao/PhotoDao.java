package dao;

import model.Photos;

public interface PhotoDao {

    public void uploadPhoto(Photos photo);

    public Photos getPhotobyId(int id);

    public void deletePhoto(int id);
}
