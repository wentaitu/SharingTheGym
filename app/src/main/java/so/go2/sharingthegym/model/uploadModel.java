package so.go2.sharingthegym.model;

import java.util.List;

/**
 * 测试Model类
 * Created by Rye on 2017/5/6.
 */

public class uploadModel {

    private int code;
    private List<ArticlesBean> articles;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public List<ArticlesBean> getArticles() {
        return articles;
    }

    public void setArticles(List<ArticlesBean> articles) {
        this.articles = articles;
    }

    public static class ArticlesBean {

        private int articleId;
        private UserBean user;
        private String articleContent;
        private int lookNum;
        private int likeNum;
        private int commentNum;
        private String date;
        private String time;
        private boolean likeArticle;
        private List<String> articleImgs;

        public int getArticleId() {
            return articleId;
        }

        public void setArticleId(int articleId) {
            this.articleId = articleId;
        }

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public String getArticleContent() {
            return articleContent;
        }

        public void setArticleContent(String articleContent) {
            this.articleContent = articleContent;
        }

        public int getLookNum() {
            return lookNum;
        }

        public void setLookNum(int lookNum) {
            this.lookNum = lookNum;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getCommentNum() {
            return commentNum;
        }

        public void setCommentNum(int commentNum) {
            this.commentNum = commentNum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public boolean isLikeArticle() {
            return likeArticle;
        }

        public void setLikeArticle(boolean likeArticle) {
            this.likeArticle = likeArticle;
        }

        public List<String> getArticleImgs() {
            return articleImgs;
        }

        public void setArticleImgs(List<String> articleImgs) {
            this.articleImgs = articleImgs;
        }

        public static class UserBean {

            private int userId;
            private String userName;
            private Object userPassword;
            private Object userEmergencyPhone;
            private String userImg;

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public Object getUserPassword() {
                return userPassword;
            }

            public void setUserPassword(Object userPassword) {
                this.userPassword = userPassword;
            }

            public Object getUserEmergencyPhone() {
                return userEmergencyPhone;
            }

            public void setUserEmergencyPhone(Object userEmergencyPhone) {
                this.userEmergencyPhone = userEmergencyPhone;
            }

            public String getUserImg() {
                return userImg;
            }

            public void setUserImg(String userImg) {
                this.userImg = userImg;
            }
        }
    }
}
