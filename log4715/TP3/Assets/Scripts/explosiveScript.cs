using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class explosiveScript : MonoBehaviour {

    [Range(0.0f, 50.0f)]
    public float explosiveDamage = 10.0f;

    [Range(0.0f, 10.0f)]
    public float explosiveDamageRadius = 2.0f;

    float explosionTime;
    bool explosion;

    [SerializeField]
    AudioClip explosionSond = null;

    AudioSource _AS;

    // Awake se produit avait le Start. Il peut être bien de régler les références dans cette section.
    void Awake()
    {
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start ()
    {
        explosionTime = 0.0f;

    }
	
	// Update is called once per frame
	void Update ()
    {
        if (explosion)
        {
            explosionTime += Time.deltaTime;
            if (explosionTime >= 2.0f)
            {
                Destroy(gameObject);
            }
        }
    }

    void Explosion()
    {
        _AS.PlayOneShot(explosionSond, 2f);
        this.gameObject.GetComponentInChildren<ParticleSystem>().Emit(10);
        this.gameObject.GetComponent<Renderer>().enabled = false;
        this.gameObject.GetComponent<BoxCollider>().enabled = false;

        GameObject player = GameObject.FindWithTag("Player");

        float distance = Vector3.Distance(player.transform.position, this.gameObject.transform.position);
        if (distance < explosiveDamageRadius)
        {
            player.SendMessage("DamagePlayer", explosiveDamage);
        }

        GameObject[] enemies = GameObject.FindGameObjectsWithTag("Enemy");
        float distance2 = 0.0f;
        foreach (GameObject enemy in enemies)
        {
            distance2 = Vector3.Distance(enemy.transform.position, this.gameObject.transform.position);
            if (distance2 < explosiveDamageRadius)
            {
                enemy.SendMessage("TakeDamage", (uint)2);
            }
        }
        
     }

    public void gasBottleHit()
    {
        Explosion();
        explosion = true;
    }
}
