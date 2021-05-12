<?php

namespace App\Controller;

use App\Entity\Recommendation;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\RecommendationRepository;
use App\Repository\UserRepository;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/api/recommendation")
 */

class ApiRecommendation extends AbstractController
{
    /**
     * @Route("/", name="api_recommendation")
     */
    public function index(RecommendationRepository $recommendationRepository, Request $request): Response
    {
        $recommendations = $recommendationRepository->findAll();

        foreach ($recommendations as $rec) {
            $date = $rec->getDate();
            $rec->setDateFormatted($date->format('Y-m-d H:i:s'));
        }


        $encoders = array(new XmlEncoder(), new JsonEncoder());
        $normalizers = array(new ObjectNormalizer());
        $normalizer = new ObjectNormalizer();
        $normalizer->setCircularReferenceLimit(2);
        // Add Circular reference handler
        $normalizer->setCircularReferenceHandler(function ($object) {
            return $object->getId();
        });
        $normalizers = array($normalizer);
        $serializer = new Serializer($normalizers, $encoders);
        $formatted = $serializer->normalize($recommendations);
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/delete/{id}", name="recommendation_deleteApi", methods={"POST"})
     */
    public function delete(Request $request, Recommendation $recommendation): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($recommendation);
        $entityManager->flush();

        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("recommendation supprimer");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/add", name="reclamation_add", methods={"GET","POST"})
     */
    public function new(Request $request, UserRepository $userRepository): Response
    {
        $recommendation = new Recommendation();
        // get user connected nt a static one
        $recommendation->setComment($request->get('comment'));
        $recommendation->setSujet($request->get('sujet'));
        $recommendation->setDate(new \DateTime());
        $recommendation->setIdutilisateur($userRepository->find($request->get('idUser')));
        $recommendation->setEtat('en cours');
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($recommendation);
        $entityManager->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($recommendation);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit", name="recommendation_editApi", methods={"GET","POST"})
     */
    public function edit(Request $request,UserRepository $userRepository, RecommendationRepository $rr ): Response
    {
        $idRecommendation = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $reclamation  =  $rr->find($idRecommendation);
        $reclamation->setComment($request->get('comment'));
        $reclamation->setSujet($request->get('sujet'));
        $reclamation->setDate(new \DateTime());
        $reclamation->setIdutilisateur($userRepository->find($request->get('idUser')));
        $reclamation->setEtat('en cours');
        $em->persist($reclamation);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("recommendation modifier");
        return new JsonResponse($formatted);

    }
}
