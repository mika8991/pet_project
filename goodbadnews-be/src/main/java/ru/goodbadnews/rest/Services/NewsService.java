package ru.goodbadnews.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import ru.goodbadnews.rest.exceptions.NotNewsFoundRepoException;
import ru.goodbadnews.rest.model.News;

import java.util.List;

@Service
public class NewsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List getAllNews() {
        List result = mongoTemplate.findAll(News.class);
        if (!result.isEmpty()) {
            return result;
        } else throw new NotNewsFoundRepoException();
    }

    public void insertNews(News newNews) {
        mongoTemplate.insert(newNews);
    }

    public News getNewsById(Long id) {
        News result = mongoTemplate.findOne(
                Query.query(Criteria.where(News.ID).is(id)), News.class);
        if (result != null) {
            return result;
        } else throw new NotNewsFoundRepoException();
    }

    public void deleteNewsById(Long id) {
        if (!mongoTemplate.find(new Query(Criteria.where(News.ID).is(id)), News.class).isEmpty()) {
            mongoTemplate.remove(getNewsById(id));
        } else throw new NotNewsFoundRepoException();
    }

    public void findAndModifyNewsById(News newNews, Long id) {
        if (!mongoTemplate
                .find(new Query(Criteria.where(News.ID).is(id)), News.class)
                .isEmpty()) {
            Query query = new Query(Criteria.where(News.ID).is(id));
            Update update = new Update();
            update.set("title", newNews.getTitle());
            update.set("goodText", newNews.getGoodText());
            update.set("badText", newNews.getBadText());
            mongoTemplate.findAndModify(query, update, News.class);
        } else throw new NotNewsFoundRepoException();
    }
}
