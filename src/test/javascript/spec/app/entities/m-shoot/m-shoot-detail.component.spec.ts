/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { TsubasaTestModule } from '../../../test.module';
import { MShootDetailComponent } from 'app/entities/m-shoot/m-shoot-detail.component';
import { MShoot } from 'app/shared/model/m-shoot.model';

describe('Component Tests', () => {
  describe('MShoot Management Detail Component', () => {
    let comp: MShootDetailComponent;
    let fixture: ComponentFixture<MShootDetailComponent>;
    const route = ({ data: of({ mShoot: new MShoot(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [TsubasaTestModule],
        declarations: [MShootDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(MShootDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(MShootDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.mShoot).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
