package com.example.android.lagosgithubdeveloper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by saint on 8/21/2017.
 */

public class DeveloperFinder extends AppCompatActivity{


    EditText etGitHubUser; //A reference to our github username input
    Button btnGetRepos;    //A reference to the 'get Repos' button
    TextView tvRepoList;   //Reference our repo list text box
    RequestQueue requestQueue; //Request Queue to processs our HTTP requests

    String baseUrl = "https://api.github.com/users/"; //The API base URL from GITHUB
    String url;  //this will hold the full URL which will be include the username entered in the etGitHubuser.



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.developerfinder_main);


        this.etGitHubUser = (EditText) findViewById(R.id.et_github_user); //Link github user text box
        this.btnGetRepos = (Button) findViewById(R.id.btn_get_repos); //linking onClick button
        this.tvRepoList = (TextView) findViewById(R.id.tv_repo_list); //links the repository list list output box
        this.tvRepoList.setMovementMethod(new ScrollingMovementMethod()); //makes the text box scrollable, in case of a big github big contributor


        requestQueue = Volley.newRequestQueue(this); //this sets up a new request queue which we will need to make HTTP requests


    }
    // help functions

    private void clearRepoList(){ // Clears the repo list

        this.tvRepoList.setText(""); //And sets it as a string

    }

    private void addToRepoList(String repoName, String lastUpdated) { //adds a new repo to our list

        String strRow = repoName + " / " + lastUpdated; //combines the repoName and lastUpdated strings together

        String currentText = tvRepoList.getText().toString(); // get them and converts it to string

        this.tvRepoList.setText(currentText + "\n\n" + strRow); // followed by a new line (\n\n make two lines)

    }

    private void setTvRepoListText(String str){ //used for the setting the text of our repo list to a specific string (message)

        this.tvRepoList.setText(str); //use to write "No repo found" message if the user doesnt have any

    }

    private void getRepoList(String username){ // First, we insert the username into the repo url.


        this.url = this.baseUrl + "/repos";  // The repo url is defined in GitHubs API docs (https://developer.github.com/v3/repos/).


        JsonArrayRequest arrReq = new JsonArrayRequest(Request.Method.GET, url,

                new Response.Listener<JSONArray>() { // Next, we create a new JsonArrayRequest. This will use Volley to make a HTTP request


                    // that expects a JSON Array Response.
                    @Override
                    public void onResponse(JSONArray response) {
                        // Check the length of our response (to see if the user has any repos)
                        if (response.length() > 0) {
                            // The user does have repos, so let's loop through them all.
                            for (int i = 0; i < response.length(); i++) {
                                try {
                                    // For each repo, add a new line to our repo list.
                                    JSONObject jsonObj = response.getJSONObject(i);
                                    String repoName = jsonObj.get("name").toString();
                                    String lastUpdated = jsonObj.get("updated_at").toString();
                                    addToRepoList(repoName, lastUpdated);
                                } catch (JSONException e) {
                                    // If there is an error then output this to the logs.
                                    Log.e("Volley", "Invalid JSON Object.");
                                }

                            }
                        } else {
                            // The user didn't have any repos.
                            setTvRepoListText("No repos found.");
                        }

                    }
                },

                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // If there's a HTTP error then add a note to our repo list.
                        setTvRepoListText("Error while calling REST API");
                        Log.e("Volley", error.toString());
                    }
                }
        );
        // Add the request we just defined to our request queue.
        // The request queue will automatically handle the request as soon as it can.
        requestQueue.add(arrReq);
    }

    public void getReposClicked(View view){

        clearRepoList();         // Clear the repo list (so we have a fresh screen to add to)

        getRepoList(etGitHubUser.getText().toString());   // Call our getRepoList() function that is defined above and pass in the
        // text which has been entered into the etGitHubUser text input field.


    }



}

