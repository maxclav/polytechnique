using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class enemyControler : MonoBehaviour {

	// Use this for initialization
	void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    void deleteEnemy()
    {
        this.gameObject.GetComponentInChildren<Renderer>().enabled = false;
    }

}
