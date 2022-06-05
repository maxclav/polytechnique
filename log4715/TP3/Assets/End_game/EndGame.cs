using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EndGame : MonoBehaviour
{

    [SerializeField]
    AudioClip win = null;

    AudioSource _AS;

    // Use this for initialization
    void Awake()
    {
        _AS = GetComponent<AudioSource>();

    }

    void OnTriggerEnter(Collider other)
    {
        if (other.tag == "Player")
        {
            _AS.PlayOneShot(win);
            GameObject.FindWithTag("GameUI").GetComponent<UIControllerScript>().SendMessage("EndGame");
        }
    }
}
