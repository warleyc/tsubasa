/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MGuildEffectLevelDetailComponent } from 'app/entities/m-guild-effect-level/m-guild-effect-level-detail.component';
import { MGuildEffectLevel } from 'app/shared/model/m-guild-effect-level.model';

describe('Component Tests', () => {
  describe('MGuildEffectLevel Management Detail Component', () => {
    let comp: MGuildEffectLevelDetailComponent;
    let fixture: ComponentFixture<MGuildEffectLevelDetailComponent>;
    const route = ({ data: of({ mGuildEffectLevel: new MGuildEffectLevel(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MGuildEffectLevelDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MGuildEffectLevelDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MGuildEffectLevelDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mGuildEffectLevel).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
