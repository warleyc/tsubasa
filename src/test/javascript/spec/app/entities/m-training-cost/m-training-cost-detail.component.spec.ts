/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCostDetailComponent } from 'app/entities/m-training-cost/m-training-cost-detail.component';
import { MTrainingCost } from 'app/shared/model/m-training-cost.model';

describe('Component Tests', () => {
  describe('MTrainingCost Management Detail Component', () => {
    let comp: MTrainingCostDetailComponent;
    let fixture: ComponentFixture<MTrainingCostDetailComponent>;
    const route = ({ data: of({ mTrainingCost: new MTrainingCost(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCostDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTrainingCostDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTrainingCostDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTrainingCost).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
