package com.ssafy.db.repository.board;

import com.ssafy.db.entity.board.*;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DogInformationSpecification {


    public static Specification<DogInformation> likeDogName(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("dogName"), "%" + keyword + "%");
            }
        };
    }


    public static Specification<DogInformation> likeAddress(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("address"), "%" + keyword + "%");
            }
        };
    }


    public static Specification<DogInformation> likeSido(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("gugun").get("sidoCode").get("name"), "%" + keyword + "%");
            }
        };
    }

    public static Specification<DogInformation> likeGugun(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("gugun").get("name") , "%" + keyword + "%");
            }
        };
    }


    public static Specification<DogInformation> likeDogType(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("dogType").get("name"), "%" + keyword + "%");
            }
        };
    }


    public static Specification<DogInformation> likeTitle(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("boardId").get("title"), "%" + keyword + "%");
            }
        };
    }


    public static Specification<DogInformation> likeDesc(final String keyword) {

        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                return cb.like(root.get("description"), "%" + keyword + "%");
            }
        };
    }

    public static Specification<DogInformation> eqSido(final Sido sido) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("gugun").get("sidoCode"), sido);
                return equalPredicate;
            }
        };
    }


    public static Specification<DogInformation> eqDogType(final DogType dogType) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("dogType"), dogType);
                return equalPredicate;
            }
        };
    }


    public static Specification<DogInformation> eqColor(final Code color) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("colorType"), color);
                return equalPredicate;
            }
        };
    }


    public static Specification<DogInformation> eqGender(final Code gender) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query,
                                         CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("gender"), gender);
                return equalPredicate;
            }
        };
    }

    public static Specification<DogInformation> eqBoardType(final BoardCategory boardCategory) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("boardId").get("type"), boardCategory);
                return equalPredicate;
            }
        };
    }


    public static Specification<DogInformation> inType(final BoardCategory boardCategory1, final BoardCategory boardCategory2) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                List<BoardCategory> list = new ArrayList<BoardCategory>();
                list.add(boardCategory1);
                list.add(boardCategory2);
                return cb.and(root.get("boardId").get("type").in(list));
            }
        };
    }

    public static Specification<DogInformation> eqAge(final Code age) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("age"), age);
                return equalPredicate;
            }
        };
    }

    public static Specification<DogInformation> eqWeight(final Code weight) {
        return new Specification<DogInformation>() {
            @Override
            public Predicate toPredicate(Root<DogInformation> root,
                                         CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate equalPredicate = cb.equal(root.get("weight"), weight);
                return equalPredicate;
            }
        };
    }

}
