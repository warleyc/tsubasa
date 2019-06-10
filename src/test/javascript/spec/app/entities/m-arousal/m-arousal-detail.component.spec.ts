/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MArousalDetailComponent } from 'app/entities/m-arousal/m-arousal-detail.component';
import { MArousal } from 'app/shared/model/m-arousal.model';

describe('Component Tests', () => {
  describe('MArousal Management Detail Component', () => {
    let comp: MArousalDetailComponent;
    let fixture: ComponentFixture<MArousalDetailComponent>;
    const route = ({ data: of({ mArousal: new MArousal(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MArousalDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MArousalDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MArousalDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mArousal).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
