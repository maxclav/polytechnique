using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class PlayerSounds : MonoBehaviour {

    [SerializeField]
    AudioClip Hurt = null;

    [SerializeField]
    AudioClip Jump = null;

    [SerializeField]
    AudioClip Death = null;

    [SerializeField]
    AudioClip Shoot = null;

    [SerializeField]
    AudioClip Burn = null;


    AudioSource _AS = null;

    private void Awake()
    {
        _AS = GetComponent<AudioSource>();
    }

    // Use this for initialization
    void Start () {
		
	}
	
	// Update is called once per frame
	void Update () {
		
	}

    public void PlayHurt()
    {
        _AS.PlayOneShot(Hurt);
    }

    public void PlayJump()
    {
        _AS.PlayOneShot(Jump);
    }

    public void PlayDeath()
    {
        _AS.PlayOneShot(Death);
    }

    public void PlayShoot()
    {
        _AS.PlayOneShot(Shoot, 0.5f);
    }

    public void PlayBurn()
    {
        _AS.PlayOneShot(Burn);
    }
}
