<?php

namespace App\Controller;

use App\Entity\Depense;
use App\Entity\Recommendation;
use App\Entity\Revenu;
use App\Repository\DepenseRepository;
use App\Repository\FicheFinanceRepository;
use Symfony\Component\HttpFoundation\JsonResponse;
use Symfony\Bundle\FrameworkBundle\Controller\AbstractController;
use Symfony\Component\HttpFoundation\Request;
use Symfony\Component\HttpFoundation\Response;
use Symfony\Component\Routing\Annotation\Route;
use App\Repository\RecommendationRepository;
use App\Repository\RevenuRepository;
use App\Repository\UserRepository;
use Knp\Bundle\SnappyBundle\Snappy\Response\PdfResponse;
use Knp\Snappy\Pdf;
use Symfony\Component\Serializer\Encoder\JsonEncoder;
use Symfony\Component\Serializer\Encoder\XmlEncoder;
use Symfony\Component\Serializer\Normalizer\ObjectNormalizer;
use Symfony\Component\Serializer\Serializer;

/**
 * @Route("/api/revenue")
 */

class ApiRevenueController extends AbstractController
{
    /**
     * @Route("/", name="api_revenue")
     */
    public function index(RevenuRepository $revenuRepository, Request $request): Response
    {
        $revenus = $revenuRepository->findAll();

        foreach ($revenus as $rec) {
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
        $formatted = $serializer->normalize($revenus);
        return new JsonResponse($formatted);
    }



    /**
     * @Route("/delete/{id}", name="revenu_deleteApi", methods={"POST"})
     */
    public function delete(Request $request, Revenu $revenu): Response
    {
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->remove($revenu);
        $entityManager->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("revenue supprimer");
        return new JsonResponse($formatted);
    }


    /**
     * @Route("/add", name="revenu_add", methods={"GET","POST"})
     */
    public function new(Request $request, UserRepository $userRepository, FicheFinanceRepository $ff): Response
    {
        $revenu = new Revenu();
        $revenu->setValeur($request->get('valeur'));
        $revenu->setSource($request->get('source'));
        $revenu->setDescription($request->get('description'));
        $revenu->setJustificatif($request->get('justificatif'));
        $revenu->setDate(new \DateTime());
        $revenu->setIdutilisateur($userRepository->find($request->get('idUser')));
        $revenu->setIdFinance($ff->find($request->get('idFinance')));
        $entityManager = $this->getDoctrine()->getManager();
        $entityManager->persist($revenu);
        $entityManager->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize($revenu);
        return new JsonResponse($formatted);
    }

    /**
     * @Route("/edit", name="revenu_editApi", methods={"GET","POST"})
     */
    public function edit(Request $request, UserRepository $userRepository, RevenuRepository $rr, FicheFinanceRepository $ff): Response
    {
        $idRevenu = $request->get('id');
        $em = $this->getDoctrine()->getManager();
        $revenu  =  $rr->find($idRevenu);


        $revenu->setValeur($request->get('valeur'));
        $revenu->setSource($request->get('source'));
        $revenu->setDescription($request->get('description'));
        $revenu->setJustificatif($request->get('justificatif'));
        $revenu->setDate(new \DateTime());
        $revenu->setIdutilisateur($userRepository->find($request->get('idUser')));
        $revenu->setIdFinance($ff->find($request->get('idFinance')));

        $em->persist($revenu);
        $em->flush();
        $serializer = new Serializer([new ObjectNormalizer()]);
        $formatted = $serializer->normalize("revenue modifier");
        return new JsonResponse($formatted);
    }


}
