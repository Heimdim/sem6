package utils;

public enum PersonTags
{
    PERSONS("persons"), PERSON("person"),ID("id"), AGE("age"), HEIGHT("height"), PREFGENDER("prefGender"),GENDER("gender"),LPREFAGE("lowPrefAge"),
    hPREFAGE("highPrefAge"), NAME("name");

    private String tag;

    PersonTags(String tag)
    {
        this.tag=tag;
    }

    public String getTag()
    {
        return tag;
    }
}
