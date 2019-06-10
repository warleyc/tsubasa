/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillCutinDetailComponent } from 'app/entities/m-action-skill-cutin/m-action-skill-cutin-detail.component';
import { MActionSkillCutin } from 'app/shared/model/m-action-skill-cutin.model';

describe('Component Tests', () => {
  describe('MActionSkillCutin Management Detail Component', () => {
    let comp: MActionSkillCutinDetailComponent;
    let fixture: ComponentFixture<MActionSkillCutinDetailComponent>;
    const route = ({ data: of({ mActionSkillCutin: new MActionSkillCutin(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillCutinDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionSkillCutinDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionSkillCutinDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mActionSkillCutin).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
