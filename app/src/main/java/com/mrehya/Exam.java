    package com.mrehya;

    import java.util.ArrayList;

    /**
     * Created by sdfsdfasf on 2/27/2018.
     */

    public class Exam {
        private int qCount;
        private int id;
        private int time;
        private String image;
        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTime() {
            return time;
        }

        public void setTime(int time) {
            this.time = time;
        }

        private String name;

        public int getqCount() {
            return questions.size();
        }

        public void setqCount() {
            this.qCount = questions.size();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public ArrayList<Question> getQuestions() {
            return questions;
        }

        public void setQuestions(ArrayList<Question> questions) {
            this.questions = questions;
        }

        private String description;
        private ArrayList<Question> questions =new ArrayList<Question>();
        private String price;

        public void setqCount(int qCount) {
            this.qCount = qCount;
        }

        public String getPrice() {
            return price;
        }

        public void setPrice(String price) {
            this.price = price;
        }

        public Exam(int id, String name, String description, String price, String image) {

            this.id = id;
            this.name = name;
            this.description = description;
            this.price = price;
            this.image = image;
        }


        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public Exam(int id, int time, String name, String description) {
            this.id = id;
            this.time = time;
            this.name = name;
            this.description = description;

        }


        public void add_Q(Question q) {
            questions.add(q);
        }

        public Question getQuestion(int index){
            return questions.get(index);
        }


    }
