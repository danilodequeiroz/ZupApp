package com.brutcode.seedapp;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

/**
 * Created by Danilo on 04/07/2016.
 */
/** This is some model to the following json
 *   {
 *        "Title": "Terminator 2: Judgment Day",
 *         "Year": "1991",
 *        "Rated": "R",
 *        "Released": "03 Jul 1991",
 *        "Runtime": "137 min",
 *        "Genre": "Action, Sci-Fi",
 *        "Director": "James Cameron",
 *        "Writer": "James Cameron, William Wisher Jr.",
 *        "Actors": "Arnold Schwarzenegger, Linda Hamilton, Edward Furlong, Robert Patrick",
 *        "Plot": "A cyborg, identical to the one who failed to kill Sarah Connor, must now protect her young son, John Connor, from a more advanced cyborg, made out of liquid metal.",
 *        "Language": "English, Spanish",
 *        "Country": "USA, France",
 *        "Awards": "Won 4 Oscars. Another 20 wins & 22 nominations.",
 *        "Poster": "http://ia.media-imdb.com/images/M/MV5BMTI4MDAwMDY3N15BMl5BanBnXkFtZTcwODIwMzMzMQ@@._V1._CR46,1,342,473_SY132_CR3,0,89,132_AL_.jpg_V1_SX300.jpg",
 *        "Metascore": "75",
 *        "imdbRating": "8.5",
 *        "imdbVotes": "734,005",
 *        "imdbID": "tt0103064",
 *        "Type": "movie",
 *        "Response": "True"
 *    }
 */
@Parcel
public class Content  implements Comparable<Content> {

    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Year")
    @Expose
    public String year;
    @SerializedName("Rated")
    @Expose
    public String rated;
    @SerializedName("Released")
    @Expose
    public String released;
    @SerializedName("Runtime")
    @Expose
    public String runtime;
    @SerializedName("Genre")
    @Expose
    public String genre;
    @SerializedName("Director")
    @Expose
    public String director;
    @SerializedName("Writer")
    @Expose
    public String writer;
    @SerializedName("Actors")
    @Expose
    public String actors;
    @SerializedName("Plot")
    @Expose
    public String plot;
    @SerializedName("Language")
    @Expose
    public String language;
    @SerializedName("Country")
    @Expose
    public String country;
    @SerializedName("Awards")
    @Expose
    public String awards;
    @SerializedName("Poster")
    @Expose
    public String poster;
    @SerializedName("Metascore")
    @Expose
    public String metascore;
    @SerializedName("imdbRating")
    @Expose
    public String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    public String imdbVotes;
    @SerializedName("imdbID")
    @Expose
    public String imdbID;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Response")
    @Expose
    public String response;

    public Content(){}

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The Year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The rated
     */
    public String getRated() {
        return rated;
    }

    /**
     *
     * @param rated
     * The Rated
     */
    public void setRated(String rated) {
        this.rated = rated;
    }

    /**
     *
     * @return
     * The released
     */
    public String getReleased() {
        return released;
    }

    /**
     *
     * @param released
     * The Released
     */
    public void setReleased(String released) {
        this.released = released;
    }

    /**
     *
     * @return
     * The runtime
     */
    public String getRuntime() {
        return runtime;
    }

    /**
     *
     * @param runtime
     * The Runtime
     */
    public void setRuntime(String runtime) {
        this.runtime = runtime;
    }

    /**
     *
     * @return
     * The genre
     */
    public String getGenre() {
        return genre;
    }

    /**
     *
     * @param genre
     * The Genre
     */
    public void setGenre(String genre) {
        this.genre = genre;
    }

    /**
     *
     * @return
     * The director
     */
    public String getDirector() {
        return director;
    }

    /**
     *
     * @param director
     * The Director
     */
    public void setDirector(String director) {
        this.director = director;
    }

    /**
     *
     * @return
     * The writer
     */
    public String getWriter() {
        return writer;
    }

    /**
     *
     * @param writer
     * The Writer
     */
    public void setWriter(String writer) {
        this.writer = writer;
    }

    /**
     *
     * @return
     * The actors
     */
    public String getActors() {
        return actors;
    }

    /**
     *
     * @param actors
     * The Actors
     */
    public void setActors(String actors) {
        this.actors = actors;
    }

    /**
     *
     * @return
     * The plot
     */
    public String getPlot() {
        return plot;
    }

    /**
     *
     * @param plot
     * The Plot
     */
    public void setPlot(String plot) {
        this.plot = plot;
    }

    /**
     *
     * @return
     * The language
     */
    public String getLanguage() {
        return language;
    }

    /**
     *
     * @param language
     * The Language
     */
    public void setLanguage(String language) {
        this.language = language;
    }

    /**
     *
     * @return
     * The country
     */
    public String getCountry() {
        return country;
    }

    /**
     *
     * @param country
     * The Country
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     *
     * @return
     * The awards
     */
    public String getAwards() {
        return awards;
    }

    /**
     *
     * @param awards
     * The Awards
     */
    public void setAwards(String awards) {
        this.awards = awards;
    }

    /**
     *
     * @return
     * The poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     *
     * @param poster
     * The Poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

    /**
     *
     * @return
     * The metascore
     */
    public String getMetascore() {
        return metascore;
    }

    /**
     *
     * @param metascore
     * The Metascore
     */
    public void setMetascore(String metascore) {
        this.metascore = metascore;
    }

    /**
     *
     * @return
     * The imdbRating
     */
    public String getImdbRating() {
        return imdbRating;
    }

    /**
     *
     * @param imdbRating
     * The imdbRating
     */
    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
    }

    /**
     *
     * @return
     * The imdbVotes
     */
    public String getImdbVotes() {
        return imdbVotes;
    }

    /**
     *
     * @param imdbVotes
     * The imdbVotes
     */
    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
    }

    /**
     *
     * @return
     * The imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     *
     * @param imdbID
     * The imdbID
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The response
     */
    public String getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The Response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Content content = (Content) o;

        if (title != null ? !title.equals(content.title) : content.title != null) return false;
        if (year != null ? !year.equals(content.year) : content.year != null) return false;
        if (rated != null ? !rated.equals(content.rated) : content.rated != null) return false;
        if (released != null ? !released.equals(content.released) : content.released != null)
            return false;
        if (runtime != null ? !runtime.equals(content.runtime) : content.runtime != null)
            return false;
        if (genre != null ? !genre.equals(content.genre) : content.genre != null) return false;
        if (director != null ? !director.equals(content.director) : content.director != null)
            return false;
        if (writer != null ? !writer.equals(content.writer) : content.writer != null) return false;
        if (actors != null ? !actors.equals(content.actors) : content.actors != null) return false;
        if (plot != null ? !plot.equals(content.plot) : content.plot != null) return false;
        if (language != null ? !language.equals(content.language) : content.language != null)
            return false;
        if (country != null ? !country.equals(content.country) : content.country != null)
            return false;
        if (awards != null ? !awards.equals(content.awards) : content.awards != null) return false;
        if (poster != null ? !poster.equals(content.poster) : content.poster != null) return false;
        if (metascore != null ? !metascore.equals(content.metascore) : content.metascore != null)
            return false;
        if (imdbRating != null ? !imdbRating.equals(content.imdbRating) : content.imdbRating != null)
            return false;
        if (imdbVotes != null ? !imdbVotes.equals(content.imdbVotes) : content.imdbVotes != null)
            return false;
        if (imdbID != null ? !imdbID.equals(content.imdbID) : content.imdbID != null) return false;
        if (type != null ? !type.equals(content.type) : content.type != null) return false;
        return response != null ? response.equals(content.response) : content.response == null;

    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (year != null ? year.hashCode() : 0);
        result = 31 * result + (rated != null ? rated.hashCode() : 0);
        result = 31 * result + (released != null ? released.hashCode() : 0);
        result = 31 * result + (runtime != null ? runtime.hashCode() : 0);
        result = 31 * result + (genre != null ? genre.hashCode() : 0);
        result = 31 * result + (director != null ? director.hashCode() : 0);
        result = 31 * result + (writer != null ? writer.hashCode() : 0);
        result = 31 * result + (actors != null ? actors.hashCode() : 0);
        result = 31 * result + (plot != null ? plot.hashCode() : 0);
        result = 31 * result + (language != null ? language.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (awards != null ? awards.hashCode() : 0);
        result = 31 * result + (poster != null ? poster.hashCode() : 0);
        result = 31 * result + (metascore != null ? metascore.hashCode() : 0);
        result = 31 * result + (imdbRating != null ? imdbRating.hashCode() : 0);
        result = 31 * result + (imdbVotes != null ? imdbVotes.hashCode() : 0);
        result = 31 * result + (imdbID != null ? imdbID.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (response != null ? response.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Content{" +
                "title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", rated='" + rated + '\'' +
                ", released='" + released + '\'' +
                ", runtime='" + runtime + '\'' +
                ", genre='" + genre + '\'' +
                ", director='" + director + '\'' +
                ", writer='" + writer + '\'' +
                ", actors='" + actors + '\'' +
                ", plot='" + plot + '\'' +
                ", language='" + language + '\'' +
                ", country='" + country + '\'' +
                ", awards='" + awards + '\'' +
                ", poster='" + poster + '\'' +
                ", metascore='" + metascore + '\'' +
                ", imdbRating='" + imdbRating + '\'' +
                ", imdbVotes='" + imdbVotes + '\'' +
                ", imdbID='" + imdbID + '\'' +
                ", type='" + type + '\'' +
                ", response='" + response + '\'' +
                '}';
    }

    @Override
    public int compareTo(Content content) {
        return getTitle().compareToIgnoreCase(content.getTitle());
    }

    //    @Override
//    public String toString() {
//        return ToStringBuilder.reflectionToString(this);
//    }
//
//    @Override
//    public int hashCode() {
//        return new HashCodeBuilder()
//                .append(title)
//                .append(year)
//                .append(rated)
//                .append(released)
//                .append(runtime)
//                .append(genre)
//                .append(director)
//                .append(writer)
//                .append(actors)
//                .append(plot)
//                .append(language)
//                .append(country)
//                .append(awards)
//                .append(poster)
//                .append(metascore)
//                .append(imdbRating)
//                .append(imdbVotes)
//                .append(imdbID)
//                .append(type)
//                .append(response)
//                .toHashCode();
//    }
//
//    @Override
//    public boolean equals(Object other) {
//        if (other == this) {
//            return true;
//        }
//        if ((other instanceof Content) == false) {
//            return false;
//        }
//        Content rhs = ((Content) other);
//        return new EqualsBuilder()
//                .append(title, rhs.title)
//                .append(year, rhs.year)
//                .append(rated, rhs.rated)
//                .append(released, rhs.released)
//                .append(runtime, rhs.runtime)
//                .append(genre, rhs.genre)
//                .append(director, rhs.director)
//                .append(writer, rhs.writer)
//                .append(actors, rhs.actors)
//                .append(plot, rhs.plot)
//                .append(language, rhs.language)
//                .append(country, rhs.country)
//                .append(awards, rhs.awards)
//                .append(poster, rhs.poster)
//                .append(metascore, rhs.metascore)
//                .append(imdbRating, rhs.imdbRating)
//                .append(imdbVotes, rhs.imdbVotes)
//                .append(imdbID, rhs.imdbID)
//                .append(type, rhs.type)
//                .append(response, rhs.response)
//                .isEquals();
//    }

}
