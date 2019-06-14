/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetCharacterGroupDetailComponent } from 'app/entities/m-target-character-group/m-target-character-group-detail.component';
import { MTargetCharacterGroup } from 'app/shared/model/m-target-character-group.model';

describe('Component Tests', () => {
  describe('MTargetCharacterGroup Management Detail Component', () => {
    let comp: MTargetCharacterGroupDetailComponent;
    let fixture: ComponentFixture<MTargetCharacterGroupDetailComponent>;
    const route = ({ data: of({ mTargetCharacterGroup: new MTargetCharacterGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetCharacterGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetCharacterGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetCharacterGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetCharacterGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
