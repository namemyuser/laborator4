package repo.FileRepositoryModifiers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class JSONRepoModifier<T> implements JSONRepoCRUD<T>{
    private final T classType;
    private final String path;


    public JSONRepoModifier(T obj, String path) {
        this.classType = obj;
        this.path = path;
    }

    @Override
    public T create(T obj) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<T> toAdd = new ArrayList<>();

        if(getAll().size() != 0){
            toAdd = getAll();
        }

        toAdd.add(obj);

        try(FileWriter writer = new FileWriter(this.path)) {
            gson.toJson(toAdd, writer);
            writer.flush();
        }

        return obj;
    }

    @Override
    public List<T> getAll() throws FileNotFoundException {

        Type REVIEW_TYPE = TypeToken.getParameterized(ArrayList.class, this.classType.getClass()).getType();
        Gson gson = new Gson();
        return gson.fromJson(new JsonReader(new FileReader(this.path)), REVIEW_TYPE);

    }

    @Override
    public T update(T oldVersion, T newVersion)  {
        boolean missionAccomplished = false;
        try {
            delete(oldVersion);
            missionAccomplished = true;
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(missionAccomplished) {
            try {
                create(newVersion);
                return oldVersion;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;

    }

    @Override
    public void delete(T obj) throws IOException {

        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        List<T> updatedRepo = new ArrayList<>();

        if(getAll().size() != 0){
            updatedRepo = getAll();
        }

        updatedRepo.removeIf(x -> x.equals(obj));

        try(FileWriter writer = new FileWriter(this.path)) {
            gson.toJson(updatedRepo, writer);
            writer.flush();
        }


    }



}