/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MTargetRarityGroupDetailComponent } from 'app/entities/m-target-rarity-group/m-target-rarity-group-detail.component';
import { MTargetRarityGroup } from 'app/shared/model/m-target-rarity-group.model';

describe('Component Tests', () => {
  describe('MTargetRarityGroup Management Detail Component', () => {
    let comp: MTargetRarityGroupDetailComponent;
    let fixture: ComponentFixture<MTargetRarityGroupDetailComponent>;
    const route = ({ data: of({ mTargetRarityGroup: new MTargetRarityGroup(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MTargetRarityGroupDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MTargetRarityGroupDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MTargetRarityGroupDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mTargetRarityGroup).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
