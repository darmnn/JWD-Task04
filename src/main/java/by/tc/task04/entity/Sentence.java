package by.tc.task04.entity;

import java.util.Objects;

public class Sentence
{
    private String content;

    public Sentence(String content)
    {
        this.content = content;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "content='" + content + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sentence sentence = (Sentence) o;
        return Objects.equals(content, sentence.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
