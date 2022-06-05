using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class powerUpScript : MonoBehaviour {

    
	
    // Use this for initialization
	void Start () {
		
	}

    private void OnTriggerEnter(Collider coll)
    {
        UnityEngine.UI.Text powerUpText = GameObject.Find("Canvas/PowerUpText").GetComponent<UnityEngine.UI.Text>();
        PowerUpTextScript textScript = powerUpText.gameObject.GetComponent<PowerUpTextScript>();
        if (coll.tag == "Player")
        {
            if (this.gameObject.name.Contains("Shield"))
            {
                GameObject canvas = GameObject.FindWithTag("UIShield");
                canvas.GetComponentInChildren<Renderer>().enabled = true;
                Destroy(this.gameObject);
                powerUpText.text = "Shield ramassé : Pesez Z pour utiliser!";
                textScript.PowerTextStart();
            }

            if (this.gameObject.name.Contains("Nuke"))
            {
                GameObject canvas = GameObject.FindWithTag("UINuke");
                canvas.GetComponentInChildren<Renderer>().enabled = true;
                Destroy(this.gameObject);
                powerUpText.text = "Nuke ramassé : Pesez X pour utiliser!";
                textScript.PowerTextStart();
            }
        }
    }
}
