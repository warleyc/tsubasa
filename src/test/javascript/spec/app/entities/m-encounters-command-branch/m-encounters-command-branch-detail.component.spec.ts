/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MEncountersCommandBranchDetailComponent } from 'app/entities/m-encounters-command-branch/m-encounters-command-branch-detail.component';
import { MEncountersCommandBranch } from 'app/shared/model/m-encounters-command-branch.model';

describe('Component Tests', () => {
  describe('MEncountersCommandBranch Management Detail Component', () => {
    let comp: MEncountersCommandBranchDetailComponent;
    let fixture: ComponentFixture<MEncountersCommandBranchDetailComponent>;
    const route = ({ data: of({ mEncountersCommandBranch: new MEncountersCommandBranch(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MEncountersCommandBranchDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MEncountersCommandBranchDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MEncountersCommandBranchDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mEncountersCommandBranch).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
