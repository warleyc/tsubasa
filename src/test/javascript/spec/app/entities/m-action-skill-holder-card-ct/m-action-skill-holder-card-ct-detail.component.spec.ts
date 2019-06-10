/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardCtDetailComponent } from 'app/entities/m-action-skill-holder-card-ct/m-action-skill-holder-card-ct-detail.component';
import { MActionSkillHolderCardCt } from 'app/shared/model/m-action-skill-holder-card-ct.model';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardCt Management Detail Component', () => {
    let comp: MActionSkillHolderCardCtDetailComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardCtDetailComponent>;
    const route = ({ data: of({ mActionSkillHolderCardCt: new MActionSkillHolderCardCt(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardCtDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionSkillHolderCardCtDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionSkillHolderCardCtDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mActionSkillHolderCardCt).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
