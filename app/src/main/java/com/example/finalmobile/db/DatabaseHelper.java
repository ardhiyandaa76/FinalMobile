    package com.example.finalmobile.db;

    import android.content.ContentValues;
    import android.content.Context;
    import android.database.Cursor;
    import android.database.sqlite.SQLiteDatabase;
    import android.database.sqlite.SQLiteOpenHelper;

    import com.example.finalmobile.models.MovieModel;

    import java.util.ArrayList;
    import java.util.List;

    public class DatabaseHelper extends SQLiteOpenHelper {
        private static final int DATABASE_VERSION = 1;
        private static final String DATABASE_NAME = "movie_database";
        private static final String TABLE_FAVORITES = "favorites";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_TITLE = "title";
        private static final String COLUMN_POSTER_PATH = "poster_path";
        private static final String COLUMN_RELEASE_DATE = "release_date";
        private static final String COLUMN_MOVIE_ID = "movie_id";
        private static final String COLUMN_VOTE_AVERAGE = "vote_average";
        private static final String COLUMN_MOVIE_OVERVIEW = "movie_overview";
        private static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        public DatabaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            String CREATE_FAVORITES_TABLE = "CREATE TABLE " + TABLE_FAVORITES + "("
                    + COLUMN_ID + " INTEGER PRIMARY KEY,"
                    + COLUMN_TITLE + " TEXT,"
                    + COLUMN_POSTER_PATH + " TEXT,"
                    + COLUMN_RELEASE_DATE + " TEXT,"
                    + COLUMN_MOVIE_ID + " INTEGER,"
                    + COLUMN_VOTE_AVERAGE + " REAL,"
                    + COLUMN_MOVIE_OVERVIEW + " TEXT"
                    + ")";
            db.execSQL(CREATE_FAVORITES_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAVORITES);
            onCreate(db);
        }

        public void addFavorite(MovieModel movie) {
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put(COLUMN_TITLE, movie.getTitle());
            values.put(COLUMN_POSTER_PATH, movie.getPoster_path());
            values.put(COLUMN_RELEASE_DATE, movie.getRelease_date());
            values.put(COLUMN_MOVIE_ID, movie.getMovie_id());
            values.put(COLUMN_VOTE_AVERAGE, movie.getVote_average());
            values.put(COLUMN_MOVIE_OVERVIEW, movie.getMovie_overview());
            db.insert(TABLE_FAVORITES, null, values);
            db.close();
        }
        public void removeFavoriteMovie(int movieId) {
            SQLiteDatabase db = this.getWritableDatabase();
            db.delete(TABLE_FAVORITES, COLUMN_MOVIE_ID + " = ?", new String[]{String.valueOf(movieId)});
            db.close();
        }


        public boolean isFavorite(String title) {
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.query(TABLE_FAVORITES, new String[]{COLUMN_ID}, COLUMN_TITLE + " = ?",
                    new String[]{title}, null, null, null, null);
            boolean isFavorite = cursor.getCount() > 0;
            cursor.close();
            db.close();
            return isFavorite;
        }



        public MovieModel getFavoriteMovieByTitle(String title) {
            SQLiteDatabase db = this.getReadableDatabase();
            String selection = COLUMN_TITLE + " = ?";
            String[] selectionArgs = { title };
            Cursor cursor = db.query(TABLE_FAVORITES, null, selection, selectionArgs, null, null, null);
            MovieModel movie = null;
            if (cursor.moveToFirst()) {
                String movieTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                String posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
                String releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE));
                int movieId = cursor.getInt(cursor.getColumnIndex(COLUMN_MOVIE_ID));
                float voteAverage = cursor.getFloat(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
                String movieOverview = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_OVERVIEW));
                movie = new MovieModel(movieTitle, posterPath, releaseDate, movieId, voteAverage, movieOverview);
            }
            cursor.close();
            db.close();
            return movie;
        }
        public List<MovieModel> getAllFavoriteMovies() {
            List<MovieModel> favoriteMovies = new ArrayList<>();
            SQLiteDatabase db = this.getReadableDatabase();
            Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_FAVORITES, null);

            if (cursor.moveToFirst()) {
                do {
                    String movieTitle = cursor.getString(cursor.getColumnIndex(COLUMN_TITLE));
                    String posterPath = cursor.getString(cursor.getColumnIndex(COLUMN_POSTER_PATH));
                    String releaseDate = cursor.getString(cursor.getColumnIndex(COLUMN_RELEASE_DATE));
                    int movieId = cursor.getInt(cursor.getColumnIndex(COLUMN_MOVIE_ID));
                    float voteAverage = cursor.getFloat(cursor.getColumnIndex(COLUMN_VOTE_AVERAGE));
                    String movieOverview = cursor.getString(cursor.getColumnIndex(COLUMN_MOVIE_OVERVIEW));
                    MovieModel movie = new MovieModel(movieTitle, posterPath, releaseDate, movieId, voteAverage, movieOverview);
                    favoriteMovies.add(movie);
                } while (cursor.moveToNext());
            }

            cursor.close();
            db.close();
            return favoriteMovies;
        }
    }
