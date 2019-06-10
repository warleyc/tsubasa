/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MCardPowerupActionSkillDetailComponent } from 'app/entities/m-card-powerup-action-skill/m-card-powerup-action-skill-detail.component';
import { MCardPowerupActionSkill } from 'app/shared/model/m-card-powerup-action-skill.model';

describe('Component Tests', () => {
  describe('MCardPowerupActionSkill Management Detail Component', () => {
    let comp: MCardPowerupActionSkillDetailComponent;
    let fixture: ComponentFixture<MCardPowerupActionSkillDetailComponent>;
    const route = ({ data: of({ mCardPowerupActionSkill: new MCardPowerupActionSkill(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MCardPowerupActionSkillDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MCardPowerupActionSkillDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MCardPowerupActionSkillDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mCardPowerupActionSkill).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
