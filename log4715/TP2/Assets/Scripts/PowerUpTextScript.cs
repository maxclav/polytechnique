using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PowerUpTextScript : MonoBehaviour {

    static float startTime;
    static bool powerUpTextActive = false;

    // Use this for initialization
    void Start ()
    {

    }
	
	// Update is called once per frame
	void Update () {

        UnityEngine.UI.Text powerUpText = GameObject.Find("Canvas/PowerUpText").GetComponent<UnityEngine.UI.Text>();

        if (powerUpTextActive && (Time.time - startTime) > 3.0f)
        {
            powerUpText.text = "";
            powerUpTextActive = false;
        }
    }

    public void PowerTextStart()
    {
        startTime = Time.time;
        powerUpTextActive = true;
    }
}
