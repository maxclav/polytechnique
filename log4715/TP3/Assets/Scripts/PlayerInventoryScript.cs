using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerInventoryScript : MonoBehaviour {


    AudioSource _AS;

    [SerializeField]
    AudioClip healSound = null;

    [SerializeField]
    AudioClip nukeSound = null;

    [SerializeField]
    AudioClip shieldSound = null;

    public bool hasShield;
    public bool hasNuke;
    public bool hasHealth;
    public bool hasSilverKey;
    public bool hasGoldKey;

    public bool shieldActive;
	public bool invincible;
    float shieldTime = 0.0f;

    // Awake se produit avait le Start. Il peut être bien de régler les références dans cette section.
    void Awake()
    {
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start ()
    {
		invincible = false;
	}
	
	// Update is called once per frame
	void Update ()
    {
        //turn on nuke
        if (Input.GetKeyUp(KeyCode.Alpha2))
        {
            if (hasNuke)
            {
                _AS.PlayOneShot(nukeSound);
                GameObject[] ennemies = GameObject.FindGameObjectsWithTag("Enemy");
                GameObject.FindWithTag("PlayerNuke").GetComponent<ParticleSystem>().Emit(5);

                foreach (GameObject ennemy in ennemies)
                {
                    if(Vector3.Distance(GameObject.FindWithTag("Player").transform.position, ennemy.transform.position) <= 10.0f)
                    {
                        ennemy.SendMessage("TakeDamage", (uint)2);
                    }
                }
                hasNuke = false;
            }
        }

        //turn on shield
        ParticleSystem glow = GameObject.FindWithTag("PlayerShieldGlow").GetComponent<ParticleSystem>();
        if (Input.GetKeyUp(KeyCode.Alpha3))
        {
            if (hasShield && !shieldActive)
            {
                _AS.PlayOneShot(shieldSound);
                shieldActive = true;
                hasShield = false;
                shieldTime = 0.0f;
            }
        }

        if (Input.GetKeyUp(KeyCode.Alpha1))
        {
            if (hasHealth)
            {
                _AS.PlayOneShot(healSound);
                GameObject.FindWithTag("Player").GetComponent<PlayerHealth>().SendMessage("HealPlayer", 50.0f);
                hasHealth = false;
            }
        }

        if (shieldActive)
        {
            glow.Play();
            shieldTime += Time.deltaTime;
            if (shieldTime > 8.0f)
            {
                shieldActive = false;
                glow.Stop();
            }
        }
    }
}
