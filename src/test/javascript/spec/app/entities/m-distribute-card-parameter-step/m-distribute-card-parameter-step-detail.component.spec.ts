/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDistributeCardParameterStepDetailComponent } from 'app/entities/m-distribute-card-parameter-step/m-distribute-card-parameter-step-detail.component';
import { MDistributeCardParameterStep } from 'app/shared/model/m-distribute-card-parameter-step.model';

describe('Component Tests', () => {
  describe('MDistributeCardParameterStep Management Detail Component', () => {
    let comp: MDistributeCardParameterStepDetailComponent;
    let fixture: ComponentFixture<MDistributeCardParameterStepDetailComponent>;
    const route = ({ data: of({ mDistributeCardParameterStep: new MDistributeCardParameterStep(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDistributeCardParameterStepDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDistributeCardParameterStepDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDistributeCardParameterStepDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDistributeCardParameterStep).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
