package br.com.treld.model;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "posts")
@CompoundIndexes({ @CompoundIndex(name = "tags", def = "{'tags' : 1}"),
		@CompoundIndex(name = "category", def = "{'category' : 1}"),
		@CompoundIndex(name = "url", def = "{'url' : 1}", unique=true) })
public class Post {

	private String id;
	@NotEmpty(message = "The post must have a title")
	private String title;
	@NotEmpty(message = "The post must have a body")
	private String body;
	private Date creationDate;
	private Date publicationDate;
	private List<String> tags;
	private String category;
	private String author;
	private String url;

	@Override
	public String toString() {
		if (StringUtils.isNotEmpty(title))
			return title;

		return super.toString();
	}

	public void addTag(String tag) {
		if (this.tags == null) {
			tags = new ArrayList<String>();
		}

		this.tags.add(tag);
	}

	public String buildUrl() {
		if (this.title != null) {
			// remove spaces in the beginner or ending of string
			this.url = this.title.replaceAll("^\\s+|\\s+$", "");
			// replacing space to -
			this.url = url.replaceAll("[ ]{1,}", "-").toLowerCase();
			// remove accents
			String norm = Normalizer.normalize(this.url, Normalizer.Form.NFD);
			this.url = norm.replaceAll("[^\\p{ASCII}]", "");
		} 

		return this.url;
	}

	public void setTitle(String title) {
		this.title = title;
		buildUrl();
	}

	/* getters and setter */

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Date publicationDate) {
		this.publicationDate = publicationDate;
	}

	public List<String> getTags() {
		return tags;
	}

	public void setTags(List<String> tags) {
		this.tags = tags;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getUrl() {
		return url;
	}
}
