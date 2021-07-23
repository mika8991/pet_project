package ru.goodbadnews.rest.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.goodbadnews.rest.exceptions.UsernameFoundException;
import ru.goodbadnews.rest.exceptions.UsersNotFoundInRepositoryException;
import ru.goodbadnews.rest.model.User;

import java.util.List;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = mongoTemplate.findOne(
                new Query(
                        Criteria.where("username").is(username)
                ), User.class);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }

    public void saveUser(User user) {
        User userFromDB = mongoTemplate.findOne(
                new Query(
                        Criteria.where("username").is(user.getUsername())
                ), User.class);

        if (userFromDB != null) {
            throw new UsernameFoundException();
        }
        mongoTemplate.insert(user);
    }

    public void deleteUser(User user) {
        User userFromDB = mongoTemplate.findOne(
                new Query(
                        Criteria.where("username").is(user.getUsername())
                ), User.class);

        if (userFromDB == null) {
            throw new UsernameNotFoundException("User not found");
        }
        mongoTemplate.remove(user);
    }

    public List<User> getAllUsers() {
        List<User> result = mongoTemplate.findAll(User.class);
        if (!result.isEmpty()) {
            return result;
        } else throw new UsersNotFoundInRepositoryException();
    }
}
