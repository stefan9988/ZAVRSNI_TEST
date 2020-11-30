
package com.ftninformatika.zavrsni_projekat.net;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = Search.TableName)
public class Search {
    public static final String TableName="search";
    public static final String FieldTitle="title";
    public static final String FieldYear="year";
    public static final String FieldId="id";
    public static final String FieldType1="type";
    public static final String FieldPoster="poster";

    @SerializedName("Title")
    @DatabaseField(columnName = FieldTitle)
    @Expose
    private String title;
    @SerializedName("Year")
    @DatabaseField(columnName = FieldYear)
    @Expose
    private String year;
    @SerializedName("imdbID")
    @DatabaseField(columnName = FieldId)
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @DatabaseField(columnName = FieldType1)
    @Expose
    private String type;
    @SerializedName("Poster")
    @DatabaseField(columnName = FieldPoster)
    @Expose
    private String poster;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

}
