using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class explosiveScript : MonoBehaviour {

    [Range(0.0f, 50.0f)]
    public float explosiveDamage = 10.0f;

    [Range(0.0f, 10.0f)]
    public float explosiveDamageRadius = 2.0f;

    static float startTime;
    static bool explosion;
    // Use this for initialization
    void Start ()
    {
        
	}
	
	// Update is called once per frame
	void Update ()
    {
        if (explosion && (Time.time - startTime) > 2.0f)
        {
            Destroy(this.gameObject);
        }
    }

    void Explosion()
    {
        GameObject player = GameObject.FindWithTag("Player");
        float distance = Vector3.Distance(player.transform.position, this.gameObject.transform.position);
        if (distance < explosiveDamageRadius)
        {
            player.SendMessage("DamagePlayer", explosiveDamage);
        }

        GameObject enemy = GameObject.FindWithTag("Enemy");
        float distance2 = Vector3.Distance(enemy.transform.position, this.gameObject.transform.position);
        if (distance2 < explosiveDamageRadius)
        {
            enemy.SendMessage("deleteEnemy");

            this.gameObject.GetComponentInChildren<ParticleSystem>().Emit(10);
            this.gameObject.GetComponent<Renderer>().enabled = false;
        }
    }

    public void gasBottleHit()
    {
        if (!explosion)
        {
            Explosion();
            startTime = Time.time;
            explosion = true;
        }
    }
}
