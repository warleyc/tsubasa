/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MDeckConditionDetailComponent } from 'app/entities/m-deck-condition/m-deck-condition-detail.component';
import { MDeckCondition } from 'app/shared/model/m-deck-condition.model';

describe('Component Tests', () => {
  describe('MDeckCondition Management Detail Component', () => {
    let comp: MDeckConditionDetailComponent;
    let fixture: ComponentFixture<MDeckConditionDetailComponent>;
    const route = ({ data: of({ mDeckCondition: new MDeckCondition(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MDeckConditionDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MDeckConditionDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MDeckConditionDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mDeckCondition).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
