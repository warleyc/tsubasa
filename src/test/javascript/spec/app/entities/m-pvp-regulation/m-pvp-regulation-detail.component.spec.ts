/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MPvpRegulationDetailComponent } from 'app/entities/m-pvp-regulation/m-pvp-regulation-detail.component';
import { MPvpRegulation } from 'app/shared/model/m-pvp-regulation.model';

describe('Component Tests', () => {
  describe('MPvpRegulation Management Detail Component', () => {
    let comp: MPvpRegulationDetailComponent;
    let fixture: ComponentFixture<MPvpRegulationDetailComponent>;
    const route = ({ data: of({ mPvpRegulation: new MPvpRegulation(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MPvpRegulationDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MPvpRegulationDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MPvpRegulationDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mPvpRegulation).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
