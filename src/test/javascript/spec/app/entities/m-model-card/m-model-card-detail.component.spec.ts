/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MModelCardDetailComponent } from 'app/entities/m-model-card/m-model-card-detail.component';
import { MModelCard } from 'app/shared/model/m-model-card.model';

describe('Component Tests', () => {
  describe('MModelCard Management Detail Component', () => {
    let comp: MModelCardDetailComponent;
    let fixture: ComponentFixture<MModelCardDetailComponent>;
    const route = ({ data: of({ mModelCard: new MModelCard(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MModelCardDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MModelCardDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MModelCardDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mModelCard).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
