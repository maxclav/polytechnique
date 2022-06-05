using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class powerUpsInput : MonoBehaviour {


    public bool shield = false;
    float shieldStartTime;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update ()
    {
        
        //turn on nuke
        if (Input.GetKeyUp(KeyCode.X))
        {
            GameObject canvasShield = GameObject.FindWithTag("UINuke");
            if (canvasShield.GetComponentInChildren<Renderer>().enabled)
            {
                canvasShield.GetComponentInChildren<Renderer>().enabled = false;
                GameObject.FindWithTag("PlayerNuke").GetComponent<ParticleSystem>().Emit(5);
            }
        }

        //turn on shield
        ParticleSystem glow = GameObject.FindWithTag("PlayerShieldGlow").GetComponent<ParticleSystem>();
        if (Input.GetKeyUp(KeyCode.Z))
        {
            GameObject canvasShield = GameObject.FindWithTag("UIShield");
            if (canvasShield.GetComponentInChildren<Renderer>().enabled)
            {
                shield = true;
                canvasShield.GetComponentInChildren<Renderer>().enabled = false;
            }
        }

        //control of shield
        if (shield && ((Time.time - shieldStartTime) > 8.0f))
        {
            shield = false;
            glow.Stop();
        }
        if (shield && !glow.isPlaying)
        {
            shieldStartTime = Time.time;
            glow.Play();
        }
    }
}
