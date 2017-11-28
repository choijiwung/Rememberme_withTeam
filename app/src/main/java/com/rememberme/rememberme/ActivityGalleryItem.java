package com.rememberme.rememberme;

/**
 * Created by samsung on 2017-11-28.
 */
// 여행갤러리 정보 담아 놓는 클래스
public class ActivityGalleryItem {

    String TourTitle;
    String TourHash;
    int imageId;

    public ActivityGalleryItem(String tourTitle, String tourHash, int imageId) {
        TourTitle = tourTitle;
        TourHash = tourHash;
        this.imageId = imageId;
    }

    public String getTourTitle() {
        return TourTitle;
    }

    public void setTourTitle(String tourTitle) {
        TourTitle = tourTitle;
    }

    public String getTourHash() {
        return TourHash;
    }

    public void setTourHash(String tourHash) {
        TourHash = tourHash;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    @Override
    public String toString() {
        return "ActivityGalleryItem{" +
                "TourTitle='" + TourTitle + '\'' +
                ", TourHash='" + TourHash + '\'' +
                ", imageId=" + imageId +
                '}';
    }
}
