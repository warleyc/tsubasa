/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MActionSkillHolderCardContentDetailComponent } from 'app/entities/m-action-skill-holder-card-content/m-action-skill-holder-card-content-detail.component';
import { MActionSkillHolderCardContent } from 'app/shared/model/m-action-skill-holder-card-content.model';

describe('Component Tests', () => {
  describe('MActionSkillHolderCardContent Management Detail Component', () => {
    let comp: MActionSkillHolderCardContentDetailComponent;
    let fixture: ComponentFixture<MActionSkillHolderCardContentDetailComponent>;
    const route = ({ data: of({ mActionSkillHolderCardContent: new MActionSkillHolderCardContent(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MActionSkillHolderCardContentDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MActionSkillHolderCardContentDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MActionSkillHolderCardContentDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mActionSkillHolderCardContent).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
