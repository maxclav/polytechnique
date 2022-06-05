using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PistolCollider : MonoBehaviour {

    private PistolController pistol;

    // It's important to have a local variable, since we use OnTriggerStay
    // to check for floor collisions at all frame and want to limit SendMessage.
    private bool isColliding = false;

    // Use this for initialization
    void Start () {
        pistol = GameObject.Find("Pistol").GetComponent<PistolController>();
	}
	
	// Update is called once per frame
	void Update () {}
    
    // Using OnTriggerEnter has an edge case: when exiting a floor and entering
    // another one simultanously, we sometimes get the enter-enter-exit sequence
    // instead of the enter-exit-enter sequence.
    void OnTriggerStay(Collider other)
    {
        if (isColliding)
        {
            // No need to send multiple messages.
            return;
        }
        else if (other.gameObject.layer == LayerMask.NameToLayer("Floor"))
        {
            isColliding = true;
            pistol.SendMessage("SetIsColliding", isColliding);
        }
    }

    void OnTriggerExit(Collider other)
    {
        if (other.gameObject.layer == LayerMask.NameToLayer("Floor"))
        {
            isColliding = false;
            pistol.SendMessage("SetIsColliding", isColliding);
        }
    }
}
