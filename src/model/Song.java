package model;

public class Song {
	protected String mArtist;
	protected String mTitle;
	protected String mVideoUrl;
	
	public Song(String mArtist, String mTitle, String mVideoUrl) {
		this.mArtist = mArtist;
		this.mTitle = mTitle;
		this.mVideoUrl = mVideoUrl;
	}

	public String getmArtist() {
		return mArtist;
	}

	public void setmArtist(String mArtist) {
		this.mArtist = mArtist;
	}

	public String getmTitle() {
		return mTitle;
	}

	public void setmTitle(String mTitle) {
		this.mTitle = mTitle;
	}

	public String getmVideoUrl() {
		return mVideoUrl;
	}

	public void setmVideoUrl(String mVideoUrl) {
		this.mVideoUrl = mVideoUrl;
	}

	@Override
	public String toString() {
		return String.format("Song: %s, by %s",mTitle ,mArtist);
	}
	
	
	

}
