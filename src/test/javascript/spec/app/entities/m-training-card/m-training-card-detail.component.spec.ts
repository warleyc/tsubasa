/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTrainingCardDetailComponent } from 'app/entities/m-training-card/m-training-card-detail.component';
import { MTrainingCard } from 'app/shared/model/m-training-card.model';

describe('Component Tests', () => {
  describe('MTrainingCard Management Detail Component', () => {
    let comp: MTrainingCardDetailComponent;
    let fixture: ComponentFixture<MTrainingCardDetailComponent>;
    const route = ({ data: of({ mTrainingCard: new MTrainingCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTrainingCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTrainingCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTrainingCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTrainingCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
