using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class CannonController : MonoBehaviour {

    [SerializeField]
    [Range(0,Mathf.Infinity)]
    float force = 800;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float cooldown = 0.5f;

    [SerializeField]
    [Range(0, Mathf.Infinity)]
    float AirFreezeDuration = 1.25f;

    private bool _PlayerIsInside = false;
    private Collider _PlayerInside = null;

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
        if (Input.GetButtonDown("Jump") && _PlayerInside != null)
        {

            Vector3 forces = transform.rotation * new Vector3(0.0f, force, 0.0f);
            _PlayerInside.GetComponent<PlayerControler>().FreezePlayer(false);
            _PlayerInside.GetComponent<Rigidbody>().velocity = new Vector3(0.0f, 0.0f, 0.0f);
            _PlayerInside.GetComponent<Rigidbody>().AddForce(forces);
            _PlayerInside.GetComponent<PlayerControler>().FreezeAirControl(AirFreezeDuration);
            
            StartCoroutine(AsyncCooldown(cooldown));

            _PlayerInside = null;
        }
    }

    private void OnTriggerEnter(Collider other)
    {
        if(other.tag == "Player")
        {
            other.GetComponent<PlayerControler>().FreezePlayer();
            other.transform.position = transform.position;

            GetComponent<Collider>().enabled = false;
            _PlayerInside = other;
        }
    }

    IEnumerator AsyncCooldown(float time)
    {
        yield return new WaitForSeconds(time);
        GetComponent<Collider>().enabled = true;
    }
}
