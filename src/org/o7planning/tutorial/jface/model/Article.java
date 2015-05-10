package org.o7planning.tutorial.jface.model;

public class Article {

  private String title;
  private String author;
  private boolean published;

  public Article(String title, String author, boolean published) {
    this.title = title;
    this.author = author;
    this.published = published;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public boolean isPublished() {
    return published;
  }

  public void setPublished(boolean published) {
    this.published = published;
  }

}
