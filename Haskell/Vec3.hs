-- File: Vec3.hs

-- Author: Christopher Camargo
-- CMPS 112: Winter 2014

-- Vec3 module declaration.
module Vec3
( Vec3 (Vec3)
,x
,y
,z
, normalize
, dot
, mult
, multv
, add
, sub
, squaredMag
, mag
, invert
, toTup
) where

-- Vec3 type is a 3D vector with elements x, y, and z.
data Vec3 = Vec3 { x :: Float, y :: Float, z :: Float } deriving (Show, Eq)

normalize :: Vec3 -> Vec3 -- Returns a normalized vector.
normalize v1 = Vec3 ((x v1)*inv) ((y v1)*inv) ((z v1)*inv)
	where inv = 1 / (mag v1)

dot :: Vec3 -> Vec3 -> Float -- Computes the dot product of two vectors.
dot v1 v2 = (x v1)*(x v2) + (y v1)*(y v2) + (z v1)*(z v2)

mult :: Vec3 -> Float -> Vec3 -- Multiplies a vector by a scalar.
mult v1 value = Vec3 ((x v1)*value) ((y v1)*value) ((z v1)*value)

multv :: Vec3 -> Vec3 -> Vec3 -- Multiplies a vector by another vector.
multv v1 v2 = Vec3 ((x v1)*(x v2)) ((y v1)*(y v2)) ((z v1)*(z v2))

add :: Vec3 -> Vec3 -> Vec3 -- Adds a vector to another vector.
add v1 v2 = Vec3 ((x v1)+(x v2)) ((y v1)+(y v2)) ((z v1)+(z v2))

sub :: Vec3 -> Vec3 -> Vec3 -- Subtract a vector from another vector.
sub v1 v2 = Vec3 ((x v1)-(x v2)) ((y v1)-(y v2)) ((z v1)-(z v2))

squaredMag :: Vec3 -> Float -- Returns the squared magnitude of a vector.
squaredMag v1 = (x v1)*(x v1) + (y v1)*(y v1) + (z v1)*(z v1)

mag :: Vec3 -> Float -- Returns the magnitude of a vector.
mag v1 = sqrt ((x v1)*(x v1) + (y v1)*(y v1) + (z v1)*(z v1))

invert :: Vec3 -> Vec3 -- Returns the inverse of a vector.
invert v1 = Vec3 (-(x v1)) (-(y v1)) (-(z v1))

toTup :: Vec3 -> (Float, Float, Float) -- Turns a vector in to a three element tuple.
toTup v = ( (min (x v) 1.0), (min (y v) 1.0), (min (z v) 1.0) )