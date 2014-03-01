-- File: Light.hs

-- Author: Christopher Camargo
-- CMPS 112: Winter 2014

-- Light module declaration.
module Light
( light
, toLight
, reflect
, toCam
, diffuse
, specular
, phong
) where

import Vec3
import Sphere

light :: Vec3 -- Main light.
light = Vec3 5.0 10.0 10.0

toLight :: Vec3 -> Vec3 -- Get a vector from the intersection point to the light.
toLight p = normalize ( sub light p )

reflect :: Vec3 -> Vec3 -> Vec3 -- Get the reflection vector of a light about a normal.
reflect p n = add (invert (toLight p)) (mult n ((dot n (toLight p)) * 2))

toCam :: Vec3 -> Vec3 -- Get a vector from the intersection point to the camera.
toCam p = normalize $ invert p

diffuse :: Sphere -> Vec3 -> Vec3 -> Vec3 -- Calculates the diffuse lighting.
diffuse sphere s n = mult (diff sphere) (max (dot n s) 0.0)

specular :: Sphere -> Vec3 -> Vec3 -> Vec3 -- Calculates the specular lighting.
specular sphere r v = mult (spec sphere) ((max (dot r v) 0.0)**(shine sphere))

phong :: Sphere -> Vec3 -> Vec3 -> (Float, Float, Float) -- Calculates the phong lighting model at a point on a sphere.
phong sphere p n = toTup 
				   $ add (ambient sphere) 
				   $ add (specular sphere (reflect p n) (toCam p)) (diffuse sphere (toLight p) n)