Shader "PowerupPro/Powerup Shader" {
	Properties{
		_Color("Main Color", Color) = (1,1,1,1)
		_Emission("Emissive Color", Color) = (0,0,0,0)
		_MainTex("Base (RGB) Trans (A)", 2D) = "white" {}
	}

		SubShader{
		Tags{ "RenderType" = "Transparent" "Queue" = "Transparent" }
		// Render into depth buffer only
		Pass{
		ColorMask 0
	}
		// Render normally
		Pass{
		ZWrite Off
		Blend SrcAlpha OneMinusSrcAlpha
		ColorMask RGB
		Material{
		Diffuse[_Color]
		Ambient[_Color]
		Shininess[_Shininess]
		Specular[_SpecColor]
		Emission[_Emission]
	}
		Lighting On
		SetTexture[_MainTex]{
		Combine texture * primary DOUBLE, texture * primary
	}
	}
	}
}