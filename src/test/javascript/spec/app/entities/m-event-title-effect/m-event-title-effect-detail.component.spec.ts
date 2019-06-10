/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEventTitleEffectDetailComponent } from 'app/entities/m-event-title-effect/m-event-title-effect-detail.component';
import { MEventTitleEffect } from 'app/shared/model/m-event-title-effect.model';

describe('Component Tests', () => {
  describe('MEventTitleEffect Management Detail Component', () => {
    let comp: MEventTitleEffectDetailComponent;
    let fixture: ComponentFixture<MEventTitleEffectDetailComponent>;
    const route = ({ data: of({ mEventTitleEffect: new MEventTitleEffect(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEventTitleEffectDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEventTitleEffectDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEventTitleEffectDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEventTitleEffect).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
