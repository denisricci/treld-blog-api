package br.com.treld.model;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection="posts")
@CompoundIndexes({
		@CompoundIndex(name = "tags", def = "{'tags.code' : 1}"),
		@CompoundIndex(name = "category", def = "{'category.code' : 1}")
})
public class Post {
	
	private String id;
	@NotEmpty(message="The post must have a title")
	private String title;
	@NotEmpty(message="The post must have a body")
	private String body;
	private Date creationDate;
	private Date publicationDate;
	private List<Tag> tags;
	private Category category;

	@Override
	public String toString() {
		if(StringUtils.isNotEmpty(title))
			return title;

		return super.toString();
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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

	public List<Tag> getTags() {
		return tags;
	}

	public void setTags(List<Tag> tags) {
		this.tags = tags;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
}
