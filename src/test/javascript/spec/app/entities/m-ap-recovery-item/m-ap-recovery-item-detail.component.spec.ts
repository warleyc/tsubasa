/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MApRecoveryItemDetailComponent } from 'app/entities/m-ap-recovery-item/m-ap-recovery-item-detail.component';
import { MApRecoveryItem } from 'app/shared/model/m-ap-recovery-item.model';

describe('Component Tests', () => {
  describe('MApRecoveryItem Management Detail Component', () => {
    let comp: MApRecoveryItemDetailComponent;
    let fixture: ComponentFixture<MApRecoveryItemDetailComponent>;
    const route = ({ data: of({ mApRecoveryItem: new MApRecoveryItem(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MApRecoveryItemDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MApRecoveryItemDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MApRecoveryItemDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mApRecoveryItem).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
